angular
  .module('app')
  .config(routesConfig);

/** @ngInject */
function routesConfig($stateProvider, $urlRouterProvider, $locationProvider) {
  $locationProvider.html5Mode(true).hashPrefix('!');
  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('app', {
      url: '/',
    })
    .state('overview', {
      url: '/overview',
      templateUrl: 'app/views/overview.html',
      controller:'OverviewController',
      controllerAs: 'vm'
    });
}

