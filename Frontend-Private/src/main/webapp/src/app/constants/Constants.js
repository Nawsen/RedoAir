/**
 * Created by MBTAZ48 on 5/09/2016.
 */

(function () {

    angular
        .module('app')
        .factory('Constants', [
            Constants
        ]);

    function Constants() {
        return {
            BASE_URL: 'http://localhost:8080/frontend-private/api/'
        }
    }

})();
