angular.module('myApp').controller('building.addOffice',['$scope','$uibModal','$ocLazyLoad','$http','$location','toaster','$stateParams',function ($scope,$uibModal,$ocLazyLoad,$http,$location,toaster,$stateParams) {
    $scope.office={}
    if($stateParams.id!=null){
        $scope.title="编辑房源"
        load($stateParams.id)
    }else{
        $scope.title="添加房源"
    }

    function load(id){
        $http.post('office/load.do',{"id":id}).success(function(data){
            $scope.office=data;

        })
    }

    var area=-1;

    function loadStatus(){
        $http.post('building/loadAllStatus.do').success(function(data) {
            $scope.statuslist=data
        });
    }
    function loadDecorate(){
        $http.post('building/loadAllDecorate.do').success(function(data) {
            $scope.decoratelist=data
        });
    }

    $scope.save=function(){

        $http.post('office/save.do',$scope.office).success(function(data){
            if(!data){
                toaster.pop('error', "保存失败");
            }else{
                toaster.pop('success',"保存成功")
                $location.path("/building/officelist")
            }
        });
    }

    loadStatus()
    loadDecorate()

    $scope.showBuildinglist=function(){
        var modalInstance = $uibModal.open({
            templateUrl: 'modules/building/buildinglistDialog.html',
            controller: 'building.buildinglistDialog',
            size: "",
            resolve: {
                obj: function () {
                    return {};
                },
                loadMyCtrl:function(){
                    return $ocLazyLoad.load("modules/building/js/buildinglistDialog.js");
                }
            }
        });
        modalInstance.result.then(function (obj) {
            if($scope.office.publishOfficebuildinglistByOfficeBuildingId==null){
                $scope.office.publishOfficebuildinglistByOfficeBuildingId={}
            }
            $scope.office.publishOfficebuildinglistByOfficeBuildingId.id=obj.id;
            $scope.office.publishOfficebuildinglistByOfficeBuildingId.title=obj.title;
        });
    }

    if($scope.office.commonDataStatusByStatusId==null){
        $scope.office.commonDataStatusByStatusId={}
        $scope.office.commonDataStatusByStatusId.id=1;
    }
    
}]);