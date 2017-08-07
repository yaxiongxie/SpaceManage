angular.module("myApp").controller("building.buildinglist", ['$scope','$uibModal','$http','toaster','confirmDialog','uploadFiles','$ocLazyLoad','$state',function($scope,$uibModal,$http,toaster,confirmDialog,uploadFiles,$ocLazyLoad,$state){
    $scope.name="xieyaxiong";
    $scope.selected=[];
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
        {name:"编号",width:"5%",columnName:"id"},
        {name:"楼盘名称",width:"20%",columnName:"title"},
        {name:"地区",width:"10%",columnName:"commonDataAreaByAreaTypeId",relateName:"countryName"},
        {name:"地址",width:"15%",columnName:"address"},
        {name:"房源数",width:"10%",columnName:"rentCount"},
        {name:"最低价",width:"10%",columnName:"rentMinArea"},
        {name:"最高价",width:"10%",columnName:"rentMaxArea"},
        {name:"出租面积",width:"10%",columnName:"rentArea"}

    ];
	$scope.operateWidth="10%";
    $scope.operations=[
        {name:"uploadFile",title:"上传图片",imgClass:"fa fa-upload"},
        {name:"editT",title:"编辑",imgClass:"fa fa-pencil-square-o"},
        {name:"deleteT",title:"删除",imgClass:"fa fa-times"},
        {name:"look",title:"查看",imgClass:"fa fa-share"},
        {name:"deleteImage",title:"删除图片",imgClass:"fa fa-delete"}
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
    	}else if(type=="uploadFile"){
            showUploadFile(id);
        }else if(type=="look"){
            window.open("http://www.yooweel.com/frontsite/building/"+id);
        }else if(type=="deleteImage"){
            confirmDialog("删除","确定删除图片吗？",function () {
                deleteImage(id)
            });
        }else{
    		edit(id);
    	}
    }

    function showUploadFile(id){
        var modalInstance = $uibModal.open({
            templateUrl: 'modules/building/uploadFile.html',
            controller: 'building.uploadFile',
            size: "lg",
            resolve: {
                obj: function () {
//                    return {categoryid:selectnode[0].id};
                    return {id:id,"tablename":"building"};
                },
                loadMyCtrl:function($ocLazyLoad){
                    return $ocLazyLoad.load("modules/building/js/uploadFile.js");
                }
            }
        });
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
    function deleteImage(id){
        var jsonData={"relateid":id,"tablename":"building"};
        $http.post('building/deleteImages.do',jsonData).success(function(){
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
    
}]);