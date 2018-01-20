angular.module('weather')
    .config(['$stateProvider', function config($stateProvider) {

        $stateProvider.state("about", {
            url: "/about",
            controller: "AboutController",
            template: "<div>О программе</div>"
        });
    }])
    .controller('AboutController', function ($scope) {
        

    });
