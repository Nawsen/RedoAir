(function () {

    angular
        .module('app')
        .controller('IndexController', [
            'NetworkService',
            'Constants',
            'AuthenticationService',
            '$state',
            '$scope',
            '$window',
            '$translate',
            IndexController
        ]);

    function IndexController(NetworkService, Constants, AuthenticationService, $state, $scope, $window, $translate) {
        var vm = this;

        vm.currentLang = 'en';

        vm.switchLang = function (lang) {
            vm.currentLang = lang;
            $translate.use(lang);
        };

        vm.bookings = function () {
            $state.go('booking-overview');
        };

        vm.login = function () {
          $state.go('account')
        };
        vm.logout = function () {
            AuthenticationService.logOut();
            $window.location.reload();
            // $state.reload();
        };

        $scope.$watch(AuthenticationService.isLoggedIn(), function () {
            vm.isLoggedIn = AuthenticationService.isLoggedIn();
        })
    }

})();

