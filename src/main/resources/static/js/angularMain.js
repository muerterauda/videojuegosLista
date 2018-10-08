'use strict';

var app=angular.module('appvideojuegos', [ 'ngRoute', 'ui.select', 'ngSanitize']);

/**
 * AngularJS default filter with the following expression:
 * "person in people | filter: {name: $select.search, age: $select.search}"
 * performs a AND between 'name: $select.search' and 'age: $select.search'.
 * We want to perform a OR.
 */
app.filter('propsFilter', function() {
    return function(items, props) {
        var out = [];

        if (angular.isArray(items)) {
            items.forEach(function(item) {
                var itemMatches = false;

                var keys = Object.keys(props);
                for (var i = 0; i < keys.length; i++) {
                    var prop = keys[i];
                    var text = props[prop].toLowerCase();
                    if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
                        itemMatches = true;
                        break;
                    }
                }

                if (itemMatches) {
                    out.push(item);
                }
            });
        } else {
            // Let the output be the input untouched
            out = items;
        }

        return out;
    }
});

app.config(function ($routeProvider) {
    $routeProvider
        .otherwise({
        templateUrl : 'loginHtml',
        controller: 'loginController'
    })
});

app.factory('internalization', function ($http) {
    function getData(url,cadenas, config) {

        var promise=$http.post(url,cadenas, config).then(function (response) {
            return response.data;
        });
        return promise;
    }
    return {getData : getData}
});
app.controller('loginController', function ($scope, $http,$location, internalization){
    var url = "/list-languages";
    var config={
        headers:{
            'Content-Type': 'application/json;charset=utf-8;'
        }
    };
    $scope.languageSelected=document.getElementById('htmlHeader').lang;
    $http.post(url, config).then(function (response) {
        $scope.Languages=response.data;

        var a=$scope.Languages.filter(function (language) {
            return language.locale===$scope.languageSelected;
        });
        if(a.length===0){
            $scope.selected=$scope.Languages[0];
        }else{
            $scope.selected=a[0];
        }

    }, function (response) {
    });
    url = "/resourceInternalization";
    var cadenas=[$scope.languageSelected,'selectIdioma'];
    internalization.getData(url, cadenas, config).then(function (promise) {
        $scope.selectIdioma= promise.selectIdioma;
    });


    $scope.cambioIdioma=function(selectedItem){
        window.location.href="/?lang="+selectedItem.locale;
    };

    $scope.submitForm = function () {
        var url = "/login";

        var customer={
            nombre:$scope.nombre,
            contrasenya: $scope.pass +''
        };

        $http.post(url, customer, config).then(function (response) {
            var numero=response.data;
            cadenas[1]='errorNotFound';
            cadenas[2]='errorPassword';
            var url = "/resourceInternalization";
            if(numero==0){
                internalization.getData(url, cadenas, config).then(function (promise) {
                    $scope.resultMessage= promise.errorNotFound;
                });
            }else if(numero==1){
                internalization.getData(url, cadenas, config).then(function (promise) {
                    $scope.resultMessage= promise.errorPassword;
                });
            }else if(numero==2){
                window.location.href="videojuegos";
            }else{
                window.location.href="homeAdmin";
            }
        }, function (response) {
        });
    }
});
