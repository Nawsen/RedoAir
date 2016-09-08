(function () {

    angular
        .module('app')
        .factory('NetworkService', [
            '$http',
            'Constants',
            NetworkService
        ]);

    function NetworkService($http, Constants) {
        var BASE_URL = Constants.BASE_URL;
        return {
            getAllSettings: function () {
                return $http.get(BASE_URL + 'settings');
            },
            postAllSettings: function (settings) {
                return $http.post(BASE_URL + 'settings', {
                    defaultProfit: settings.defaultProfit
                });
            },
            getFlightSettings: function () {
                return $http.get(BASE_URL + 'settings/flights');
            },
            postFlightSettings: function (settings) {
                return $http.post(BASE_URL + 'settings/flights',
                    settings
                );
            }
        }
    }

})();
