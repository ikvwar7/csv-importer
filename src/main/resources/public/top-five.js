var topFive = angular.module('topFive', []);
topFive.controller('TopFiveController', function TopFiveController($scope, $http) {
    $http({
        method: 'GET',
        url: 'http://localhost:8080/reports/topFive'
    }).then(function success(response) {
            $scope.report = response.data;
        },
        function errorCallback(response) {
            console.log("error");
        }
    )
});