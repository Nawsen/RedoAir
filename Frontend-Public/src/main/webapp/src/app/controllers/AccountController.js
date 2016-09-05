(function () {

    angular
        .module('app')
        .controller('AccountController', [
            'NetworkService',
            'BookingService',
            accountController
        ]);

    function accountController(NetworkService, BookingService) {
        var vm = this;
        vm.register = function () {
            NetworkService.postRegister(vm.user).then(function () {
                Materialize.toast('Successfully registered!', 1000);
            });
        };





        $(document).ready(function () {
            $('ul.tabs').tabs();
        });

    }
})();
