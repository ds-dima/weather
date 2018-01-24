angular.module('weather')
    .config(['$stateProvider', function config($stateProvider) {

        $stateProvider.state("about", {
            url: "/about",
            templateUrl: "app/about/about.html"
        });
    }]);
