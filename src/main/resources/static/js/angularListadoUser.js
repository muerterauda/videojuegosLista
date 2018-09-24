var app=angular.module('appTableUser', []);

app.controller('listadoController',function ($scope, $http){
    var url = "/list-videojuegos";

    var config={
        headers:{
            'Content-Type': 'application/json;charset=utf-8;'
        }
    };
    $http.post(url, config).then(function (response) {
        $scope.lista=response.data;
    }, function (response) {
    });

});