var gulp = require('gulp');
var gutil = require('gulp-util');
var moveTo = './target/frontend/';
var filesToMove = ['./frontend/**'];
var filesToZip = ['./target/**']
gulp.task('move', function () {
  gulp.src(filesToMove, {base: './'}).pipe(gulp.dest('target'));
});
gulp.task('default', ['move']);