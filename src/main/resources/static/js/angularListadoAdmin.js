var app=angular.module('appTableAdmin', ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/add',{
            templateUrl : 'addAdmin',
            controller: 'add'
        })
        .when('/edit',{
            templateUrl : 'editAdmin',
            controller: 'edit'
        }  )
        .otherwise({
            templateUrl : 'tablaAdmin',
            controller: 'listadoController'
        });
});

app.factory('editVideojuego', function () {

    var videojuego = {
        id: Number,
        name: String,
        company: String,
        rating: null
    };

    function setName(nombre) {
        videojuego.name = nombre;
    }

    function setCompany(company) {
        videojuego.company = company;
    }
    function setRating(rating) {
        videojuego.rating = rating;
    }
    function setId(id) {
        videojuego.id = id;
    }

    function getVideojuego() {
        return videojuego;
    }
    function setVideojuego(videojuego){
        setId(videojuego.id);
        setName(videojuego.name);
        setCompany(videojuego.company);
        setRating(videojuego.rating);
    }

    return {
        setVideojuego: setVideojuego,
        getVideojuego: getVideojuego
    };

});
app.factory('ratingFilters', function ($filter) {
    function getRatingByIdFilter(lista,rating_id) {
        var found=$filter('getById')(lista, rating_id);
        return found;
    };
    return {
        getRatingByIdFilter: getRatingByIdFilter
    };
});
app.filter('getById', function(){
    return function (input,id) {
        var i=0, len=input.length;
        for (; i<len; i++) {
            if (+input[i].id == +id) {
                return input[i];
            }
        }
        return null;
    }
});

app.controller('listadoController',function ($scope, $http, $location, $route, editVideojuego){
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

    $scope.sortType     = 'name'; //company, rating
    $scope.sortReverse  = false;
    $scope.searchVideogame  = '';

    $scope.add =function(){
        $location.path('/add');
        $route.reload();
    };

    $scope.editar =function(videojuego){
        editVideojuego.setVideojuego(videojuego);
        $location.path('/edit');
        $route.reload();
    };

    $scope.cambiarIcono =function(valor){
        if(valor==$scope.sortType){
            $scope.sortReverse  = !$scope.sortReverse;
        }else{
            $scope.sortType =  valor;
            $scope.sortReverse  = false;
        }
    };

    $scope.delete =function(id){
        var url2= "/delete";
        var data=id;
        $http.post(url2, data, config).then(function (response) {
            $route.reload();
        }, function (response) {
        });
    };
});

app.controller('add', function ($scope, $http, $location, $route, $filter, ratingFilters){
    var url = "/list-ratings";

    var config={
        headers:{
            'Content-Type': 'application/json;charset=utf-8;'
        }
    };
    $http.post(url, config).then(function (response) {
        $scope.lista=response.data;
    }, function (response) {
    });
    $scope.add =function(){
        var url="/add";
        var videojuego={
            id: $scope.id,
            name:$scope.name,
            company: $scope.company,
            rating: null
        };
        var config={
            headers:{
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };
        videojuego.rating=ratingFilters.getRatingByIdFilter($scope.lista,$scope.pegi);
        $http.post(url, videojuego, config).then(function (response) {
            var numero=response.data;
            if(numero==1){
                $scope.resultMessage = "Error con los datos";
            }else if(numero==0){
                $scope.resultMessage = "";
                $location.path('');
                $route.reload();
            }else if(numero==2){
                $scope.resultMessage = "Ya existe un videojuego con el mismo nombre";
            }
        }, function (response) {
        });
    };

});
app.controller('edit',function ($scope, $http, $location, $route, editVideojuego, ratingFilters){
    var url = "/list-ratings";

    var config={
        headers:{
            'Content-Type': 'application/json;charset=utf-8;'
        }
    };
    $http.post(url, config).then(function (response) {
        $scope.lista=response.data;
    }, function (response) {
    });
    var v=editVideojuego.getVideojuego();
    $scope.id=v.id;
    $scope.name=v.name;
    $scope.company=v.company;
    if(v.rating!=null){
        $scope.pegi=v.rating.id.toString();
    }
    $scope.edit =function(){
        var url="/edit"
        var videojuego={
            id:$scope.id,
            name:$scope.name,
            company: $scope.company,
            rating: ratingFilters.getRatingByIdFilter($scope.lista,$scope.pegi)
        };
        var config={
            headers:{
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };
        $http.post(url, videojuego, config).then(function (response) {
            var numero=response.data;
            if(numero==1||numero==2){
                $scope.resultMessage = "Error con los datos";
            }else if(numero==0){
                $scope.resultMessage = "";
                $location.path('');
                $route.reload();
            }else if(numero==3){
                $scope.resultMessage = "Ya existe un videojuego con el mismo nombre";
            }
        }, function (response) {
        });
    };
});
