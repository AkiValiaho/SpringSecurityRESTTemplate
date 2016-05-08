package com.valiaho.Service;

import com.valiaho.DAO.ImageRepository;
import com.valiaho.Domain.Builders.PersistImageObjectBuilder;
import com.valiaho.Domain.Comment;
import com.valiaho.Domain.Image;
import com.valiaho.Utils.MultiPartFileUtils;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by akivv on 7.4.2016.
 */
@Service
public abstract class AbstractImageService implements ImageService {
	@Autowired
	MultiPartFileUtils multiPartFileUtils;
	@Autowired
	ImageRepository imageRepository;
	private Logger logger = Logger.getLogger(AbstractImageService.class);

	@Override
	public Optional<Image> persistImage(MultipartFile file, @Nullable String imageName,
										@Nullable List<Comment> comments) throws RuntimeException {
		try {
			String[] imageFullPathAndName = saveMultipartImageToPersistence(file, imageName, comments);
			final Image savedImage = saveImageWithImageName(imageFullPathAndName[0], imageFullPathAndName[1], comments);
			saveMultipartImageToPersistence(file, imageName, comments);
			return Optional.of(savedImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Optional<Image> getImageById(String documentId) {
		final Image byid = imageRepository.findOne(documentId);
		if (byid == null) {
			return Optional.empty();
		} else {
			return Optional.of(byid);
		}
	}

	private String[] saveMultipartImageToPersistence(MultipartFile file, String imageName,
													 @Nullable List<Comment> comments) throws IOException {
		String[] strings;
		if (imageName.isEmpty()) {
			strings = multiPartFileUtils.saveMultiPartFileToPersistence(file, null);
			imageName = strings[0];
			this.logger.info("Image name was generated automatically");
		} else {
			strings = multiPartFileUtils.saveMultiPartFileToPersistence(file, imageName);
			this.logger.info("Image name was provided as a parameter");
		}
		return strings;
	}

	/**
	 * @param imageFolderPathInSys
	 * @param imageName
	 * @param comments
	 * @return
	 */
	private Image saveImageWithImageName(String imageFolderPathInSys, String imageName,
										 @Nullable List<Comment> comments) {
		final Image build = getImageToPersist(imageFolderPathInSys, imageName);
		if (comments != null) {
			build.setCommentList(comments);
		}
		final Optional<Image> imagePresentinDatabase = getImageById(build.getDocumentId());

		if (imagePresentinDatabase.isPresent()) {
			try {
				final Image image = imagePresentinDatabase.get();
			} catch (ClassCastException e) {
				// database seems to be empty
				return upsertImage(imageFolderPathInSys, imageName, build, false);
			}
			// Image is present in database, use upsert with true
			return upsertImage(imageFolderPathInSys, imageName, build, true);
		} else {
			// Image is not present in database, use upsert with boolean false
			return upsertImage(imageFolderPathInSys, imageName, build, false);
		}
	}

	private Image upsertImage(String imageFolderPathInSys, String imageName, Image build, Boolean imagePresent) {
		if (imagePresent) {
			final Image image = imagePresentInDatabase(build);
			this.logger.info("Image was present in database");
			return image;
		} else {
			this.logger.info("Image was not present in database");
			return imageNotPresentInDatabase(imageFolderPathInSys, imageName, build);
		}
	}

	private Image imageNotPresentInDatabase(String imageFolderPathInSys, String imageName, Image build) {
		final Image savedImage = imageRepository.save(build);
		final Optional<Image> byDocumentId = getImageById(imageFolderPathInSys);
		handleReturnedSavedImageNull(imageName, savedImage);
		this.logger.info("Image was succesfully persisted to Couchbase NoSQL-server");
		return savedImage;
	}

	private Image imagePresentInDatabase(Image imagePresentInDB) {
		final Image updatedImage = updateImageInformationInDatabase(imagePresentInDB);
		return updatedImage;
	}

	private Image updateImageInformationInDatabase(Image byDocumentId1) {
		imageRepository.delete(byDocumentId1.getDocumentId());
		final Image save = imageRepository.save(byDocumentId1);
		return save;
	}

	private void handleReturnedSavedImageNull(String imageName, Image savedImage) {
		if (savedImage == null) {
			// Using logger from apache js4f
			this.logger.error("Failed to save the image with image name " + imageName);
			throw new RuntimeException("Failed to save the image with image name " + imageName);
		}
	}

	private Image getImageToPersist(String imageFolderPathInSys, String imageName) throws IllegalArgumentException {
		if (imageName == null || imageName.isEmpty()) {
			throw new IllegalArgumentException("Argument name is not valid");
		}
		final PersistImageObjectBuilder persistImageObjectBuilder = initializePersistImageObjectBuilderProperties(
				imageFolderPathInSys, imageName);
		return persistImageObjectBuilder.build();
	}

	private PersistImageObjectBuilder initializePersistImageObjectBuilderProperties(String imageFolderPathInSys,
																					String imageName) {
		final PersistImageObjectBuilder persistImageObjectBuilder = ImageBuilderFactory
				.buildPersistImageObjectBuilder();
		persistImageObjectBuilder.setImageName(imageName);
		final String imageLocationInFileSystem = buildImageLocation(imageFolderPathInSys, imageName);
		persistImageObjectBuilder.setImageLocationInSystem(imageLocationInFileSystem);
		return persistImageObjectBuilder;
	}

	private String buildImageLocation(String imageFolderPathInSys, String imageName) {
		return imageFolderPathInSys + "/" + imageName;
	}
}
