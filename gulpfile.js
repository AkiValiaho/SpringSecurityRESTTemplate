var gulp = require('gulp');
var gutil = require('gulp-util');
var moveTo = './target/frontend/';
var browserify = require('browserify');
var reactify = require('reactify');
var filesToMove = ['./frontend/**', './keystore.p12', './buildconfiguration.sh'];
gulp.task('move', function () {
  gulp.src(filesToMove, {base: './'}).pipe(gulp.dest('target'));
});
gulp.task('default', ['move']);