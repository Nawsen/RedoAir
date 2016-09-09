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
                console.log(vm.login.user.email);
                console.log(vm.login.user.password);
                AuthenticationService.login({
                    email: vm.user.email,
                    password: vm.user.password
                }, proceed, $stateParams.fromFlight);
            });
            // proceed($stateParams.fromFlight);
        };

        vm.login = {};
        vm.login.user = {
            // "email": "mathias@bulte.xyz",
            // "password":"wachtwoord"
        };

        vm.logIn = function () {
            AuthenticationService.login({
                email: vm.login.user.email,
                password: vm.login.user.password
            }, proceed, $stateParams.fromFlight);

            // if (AuthenticationService.isLoggedIn()) {
            //     proceed();
            // }
        };

        // function proceed() {
        //     if ($stateParams.fromFlight) {
        //         $state.go('booking');
        //     } else {
        //         $state.go('overview');
        //     }
        // }
        function proceed(flight) {
            if (flight) {
                $state.go('booking');
            } else {
                console.log("pipikaka");
                $state.go('overview');
            }
        }
        // AuthenticationService.currentUser();

        $(document).ready(function () {
            $('ul.tabs').tabs();
        });

    }
})();
