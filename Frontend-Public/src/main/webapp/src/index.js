angular
  .module('app', ['ui.router', 'ngAnimate', 'ui.materialize', 'pascalprecht.translate'])
  .service();
angular
    .module('app').config(['$translateProvider', function ($translateProvider) {
    $translateProvider.translations('en', {
        'TITLE': 'Hello',
        'FOO': 'This is a paragraph'
    });

    $translateProvider.translations('de', {
        'TITLE': 'Hallo',
        'FOO': 'Dies ist ein Absatz'
    });

    $translateProvider.preferredLanguage('en');
}]);
