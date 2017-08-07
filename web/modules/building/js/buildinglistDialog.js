angular.module("myApp").controller("building.buildinglistDialog", ['$scope','$uibModalInstance','$uibModal','$http','toaster','confirmDialog','uploadFiles','$ocLazyLoad','$state','obj',function($scope,$uibModalInstance,$uibModal,$http,toaster,confirmDialog,uploadFiles,$ocLazyLoad,$state,obj){
    $scope.name="xieyaxiong";
    $scope.selected=[];
    $scope.hideDelete="true"
    $scope.updateSelection = function($event, id){
        var checkbox = $event.target;
        if(id=='all'){
            $scope.selected=[];
            $("input[name='cbname']").each(function(){
                $(this).prop("checked",checkbox.checked);
                if(checkbox.checked){
                    $scope.selected.push($(this).attr("cid"));
                }
            })
            return ;
        }
        var action = (checkbox.checked?'add':'remove');
        if(action == 'add' && $scope.selected.indexOf(id) == -1){
            $scope.selected.push(id);
        }
        if(action == 'remove' && $scope.selected.indexOf(id)!=-1){
            var idx = $scope.selected.indexOf(id);
            $scope.selected.splice(idx,1);
        }
    }
    $scope.columns=[
        {name:"编号",width:"10%",columnName:"id"},
        {name:"楼盘名称",width:"20%",columnName:"title"},
        {name:"所在地区",width:"15%",columnName:"commonDataAreaByAreaTypeId",relateName:"countryName"},
        {name:"地址",width:"15%",columnName:"address"},
        {name:"地铁",width:"10%",columnName:"commonDataSubwayBySubwayId",relateName:"subwayName"},
        {name:"出租面积",width:"20%",columnName:"rentArea"}

    ];

    $scope.pageOption={"currentPage":1,"pageSize":12};
    $scope.pageChanged = function() {
        refreshTable();
    };
    $scope.deleteBatch=function(){
        confirmDialog("删除","确定删除吗？",function () {
            var jsonData={ids:$scope.selected.join(",")};
            $http.post('building/deleteByIds.do',jsonData).success(function(){
                refreshTable();
            });
        });
    }

    $scope.clickOperate=function(id,type){
        if(type=="deleteT"){
            confirmDialog("删除","确定删除吗？",function () {
                deleteE(id);
            });
        }else{
            edit(id);
        }
    }

    function edit(id){
        $state.go('building/addBuilding', {id: id})
    }

    function deleteE(id){
        var jsonData={"id":id};
        $http.post('building/delete.do',jsonData).success(function(){
            refreshTable();
        });
    }

    $scope.queryTable=function(){
        $scope.pageOption.currentPage=1;
        refreshTable();
    }

    $scope.addPerson=function(){
        var selectnode=$('#tree').treeview('getSelected');
        var modalInstance = $uibModal.open({
            templateUrl: 'modules/core/addBuilding.html',
            controller: 'core.addPerson',
            size: "",
            resolve: {
                obj: function () {
                    return {department:selectnode[0].text};
                },
                loadMyCtrl:function(){
                    return $ocLazyLoad.load("modules/core/js/addBuilding.js");
                }
            }
        });
        modalInstance.result.then(function (obj) {
            var fileObj=obj.fileObj;
            obj.fileObj=undefined;
            obj.departmentId=selectnode[0].id;
            $http.post('core/savePerson.do',obj).success(function(data){
                fileObj.model.relateId=data.id;
                uploadFiles(fileObj);
            });
        });
    }


    function refreshTable(){
        $http.post('building/loadPage.do',$scope.pageOption).success(function(data) {
            $scope.pageOption.currentPage=data.currentPage;
            $scope.page=data;
        });
    }

    function loadArea(){
        $http.post('building/loadAllArea.do').success(function(data) {
            $scope.arealist=data

        });
    }
    function loadSubway(){
        $http.post('building/loadAllSubway.do').success(function(data) {
            $scope.subwaylist=data
        });
    }
    refreshTable();
    loadArea();
    loadSubway();

    $scope.pageOption.areaid="0";
    $scope.pageOption.subwayid="0";

    $scope.ok = function () {
        if($scope.selected.length==0){
            toaster.pop('error', "请选择楼盘");
            return ;
        }
        var selectId=$scope.selected[0];
        var selectTitle="";
        for(var i=0;i<$scope.page.list.length;i++){
            if($scope.page.list[i].id==selectId){
                selectTitle=$scope.page.list[i].title;
                break;
            }
        }
         $uibModalInstance.close({"id":selectId,"title":selectTitle});
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

}]);