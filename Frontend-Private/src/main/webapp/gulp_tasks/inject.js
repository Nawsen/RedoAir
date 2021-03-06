const gulp = require('gulp');
const browserSync = require('browser-sync');
const wiredep = require('wiredep').stream;
const angularFilesort = require('gulp-angular-filesort');
const gulpInject = require('gulp-inject');

const conf = require('../conf/gulp.conf');

gulp.task('inject', inject);

function inject() {
  const injectStyles = gulp.src([
  conf.path.src('**/*.css')
  // conf.path.src('../bower_components/materialize/dist/css/materialize.css')
  ], {read: false});

  const injectScripts = gulp.src([
    conf.path.tmp('index.js'),
    conf.path.tmp('app/constants/*.js'),
    conf.path.tmp('app/containers/*.js'),
    conf.path.tmp('app/components/*.js'),
    conf.path.tmp('../bower_components/angular-aria/angular-aria.js'),
    conf.path.tmp('../bower_components/angular-animate/angular-animate.js'),
    conf.path.tmp('../bower_components/angular-translate/angular-translate.js'),
    // conf.path.tmp('../bower_components/materialize/dist/js/materialize.js'),
    conf.path.tmp('../bower_components/angular-materialize/src/angular-materialize.js'),
    conf.path.tmp('**/*.js'),
    conf.path.tmp('**/*.spec.js')
  ]);

  const injectOptions = {
    ignorePath: [conf.paths.src, conf.paths.tmp],
    addRootSlash: false
  };

  return gulp.src(conf.path.src('index.html'))
    .pipe(gulpInject(injectStyles, injectOptions))
    .pipe(gulpInject(injectScripts, injectOptions))
    .pipe(wiredep(Object.assign({}, conf.wiredep)))
    .pipe(gulp.dest(conf.paths.tmp))
    .pipe(browserSync.stream());
}
