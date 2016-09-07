(function () {

    angular
        .module('app')
        .controller('OverviewController', [
            '$scope',
            'NetworkService',
            'AuthenticationService',
            '$state',
            OverviewController
        ]);

    function OverviewController($scope, NetworkService, AuthenticationService, $state) {
        var vm = this;
        if (!AuthenticationService.isLoggedIn()) {
            $state.go('account');
        }

    }

})();
