angular.module('myApp').controller('building.addBuilding',['$scope','$http','$location','toaster','$stateParams',function ($scope,$http,$location,toaster,$stateParams) {
    if($stateParams.id!=null){
        $scope.title="编辑楼盘"
        load($stateParams.id)
    }else{
        $scope.title="添加楼盘"
    }

    function load(id){
        $http.post('building/load.do',{"id":id}).success(function(data){
            $scope.building=data;

        })
    }

    var area=-1;
    $scope.selectArea=function(){
        area=$scope.building.commonDataAreaByAreaTypeId.id;
        $scope.building.commonDataDistrictByDistrictTypeId=null;
        loadDistrict()
    }

    function loadArea(){
        $http.post('building/loadAllArea.do').success(function(data) {
            $scope.arealist=data
        });
    }

    function loadDistrict(){
        var q={"key":area}
        $http.post('building/loadDistrictById.do',q).success(function(data) {
            $scope.districtlist=data
        });
    }
    function loadSubway(){
        $http.post('building/loadAllSubway.do').success(function(data) {
            $scope.subwaylist=data
        });
    }
    function loadStatus(){
        $http.post('building/loadAllStatus.do').success(function(data) {
            $scope.statuslist=data
        });
    }

    $scope.save=function(){
        $http.post('building/save.do',$scope.building).success(function(data){
            if(!data){
                toaster.pop('error', "保存失败");
            }else{
                toaster.pop('success',"保存成功")
                $location.path("/building/buildinglist")
            }
        });
    }

    loadArea();
    loadDistrict()
    loadSubway()
    loadStatus()
    
}]);