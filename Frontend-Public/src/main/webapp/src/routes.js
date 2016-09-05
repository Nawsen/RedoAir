angular
  .module('app')
  .config(routesConfig);

/** @ngInject */
function routesConfig($stateProvider, $urlRouterProvider, $locationProvider) {
  $locationProvider.html5Mode(true).hashPrefix('!');
  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('overview', {
      url: '/',
      templateUrl: 'app/views/overview.html',
      controller:'OverviewController',
      controllerAs: 'vm'
    })
    .state('flight', {
      url: '/flights?departure&arrival',
      templateUrl: 'app/views/flights.html',
      controller:'FlightController',
      controllerAs: 'vm'
    })
    .state('account', {
      url: '/auth',
      templateUrl: 'app/views/account.html',
      controller:'AccountController',
      controllerAs: 'vm'
    })
      .state('booking', {
      url: '/booking',
      templateUrl: 'app/views/booking.html',
      controller:'BookingController',
      controllerAs: 'vm'
    });
}

