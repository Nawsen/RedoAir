(function () {

    angular
        .module('app')
        .controller('AccountController', [
            'NetworkService',
            'BookingService',
            'AuthenticationService',
            '$state',
            '$stateParams',
            accountController
        ]);

    function accountController(NetworkService, BookingService, AuthenticationService, $state, $stateParams) {
        if (AuthenticationService.isLoggedIn()) {
            $state.go('overview');
        }
        var vm = this;
        vm.register = function () {
            NetworkService.postRegister(vm.user).then(function () {
                Materialize.toast('Successfully registered!', 1000);
            });
            AuthenticationService.login({
                email: vm.login.user.email,
                password: vm.login.user.password
            });
            proceed();
        };

        vm.login = {};
        vm.login.user = {
            "email": "mathias@bulte.xyz",
            "password":"wachtwoord"
        };

        vm.logIn = function () {
            AuthenticationService.login({
                email: "mathias@bulte.xyz",
                password: "wachtwoord"
            }, proceed);

        };

        function proceed() {
            $state.go('overview');
        }
        // AuthenticationService.currentUser();

        $(document).ready(function () {
            $('ul.tabs').tabs();
        });

    }
})();
