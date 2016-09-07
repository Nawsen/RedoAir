/**
 * Created by Mathias on 29-Oct-15.
 */
angular
    .module('app')
    .factory('AuthenticationService', ['$http', '$window', 'NetworkService', 'Constants', function ($http, $window, NetworkService, Constants) {
        var auth = {};
        var BASE_URL = Constants.BASE_URL;
        auth.saveToken = function (token) {
            $window.localStorage['redoAir'] = token;
        };

        auth.getToken = function () {
            return $window.localStorage['redoAir'];
        };
        auth.isLoggedIn = function () {
            var token = auth.getToken();
            if (token) {
                auth.setHeader();
                return true;
            } else {
                return false;
            }
        };
        // auth.currentUser = function () {
        //     if (auth.isLoggedIn()) {
        //         var token = auth.getToken();
        //         console.log(token);
        //         // var payload = JSON.parse($window.atob(token.split('.')[1]));
        //
        //         return payload.jti;
        //     }
        // };
        auth.register = function (user) {
            return $http.post('/backend/api/users/register', user).success(function (data) {
                //auth.saveToken(data.token);
                //wordt toch niet teruggestuurd bij register.
            });
        };

        auth.login = function (user) {
            $http.post(BASE_URL + 'customer/login', user).then(function (response) {
                auth.saveToken(response.data);
                auth.setHeader();
                Materialize.toast('Successfully logged in!', 1000);
            }, function () {
                Materialize.toast("Login failed", 1000);
            });
            // $http(
            //     {
            //         method: 'POST',
            //         url: BASE_URL + 'customer/login',
            //         data: user
            //     }
            // ).then(function successCallback(response) {
            //
            // }, function errorCallback(response) {
            //
            // });
        };
        auth.setHeader = function () {
            $http.defaults.headers.common.Authorization = 'Bearer ' + auth.getToken();
        };
        auth.logOut = function () {
            $window.localStorage.removeItem('redoAir');
        };
        return auth;
    }]);
