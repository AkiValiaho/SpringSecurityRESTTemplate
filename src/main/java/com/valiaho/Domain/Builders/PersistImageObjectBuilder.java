package com.valiaho.Domain.Builders;

import com.valiaho.Domain.Comment;
import com.valiaho.Domain.Image;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by akivv on 8.4.2016.
 */
public class PersistImageObjectBuilder extends AbstractImageObjectBuilder {
	private List<Comment> commentList = new ArrayList<>();

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public PersistImageObjectBuilder clonePersistedImage(Image imageToCopy) {
		setImageLocationInSystem(imageToCopy.getImageLocation());
		setImageName(imageToCopy.getDocumentId());
		addCommentsToList(imageToCopy);
		return this;
	}

	@Override
	public Image build() throws IllegalArgumentException {
		final Image build = super.build();
		build.setCommentList(this.commentList);
		return build;
	}

	private void addCommentsToList(Image imageToCopy) {
		// Add comments to list
		if (imageToCopy.getCommentList() != null) {
			final List<Comment> commentList = imageToCopy.getCommentList();
			for (Iterator<Comment> iterator = commentList.iterator(); iterator.hasNext(); ) {
				Comment next = iterator.next();
				this.commentList.add(next);
			}
		}
	}


}
