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
            controller: 'OverviewController',
            controllerAs: 'vm'
        })
        .state('account', {
            url: '/auth',
            templateUrl: 'app/views/account.html',
            controller: 'AccountController',
            controllerAs: 'vm'
        })
        .state('appsettings', {
            url: '/appsettings',
            templateUrl: 'app/views/appsettings.html',
            controller: 'AppsettingsController',
            controllerAs: 'vm'
        })
        .state('flightSettings', {
            url: '/flightsettings',
            templateUrl: 'app/views/flights.html',
            controller: 'FlightController',
            controllerAs: 'vm'
        });
}

