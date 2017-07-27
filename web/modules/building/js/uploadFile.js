angular.module('myApp').controller('building.uploadFile', function ($scope, $uibModalInstance, obj,$http) {
	
    $scope.document=obj;
    
    $scope.cancel = function () {

        var url='building/refreshUpload.do?relateid='+$scope.document.id+"&tablename="+$scope.document.tablename;
        $http.get(url).success(function(){
            $uibModalInstance.dismiss('cancel');
        });

    };
    
    $scope.files = [];
    $scope.$on("fileSelected", function (event, args) {
        $scope.$apply(function () {
        	$scope.files = [];
            $scope.files.push(args.file);
        });
    });
    
    $scope.$on('ngLoadpageFinished', function (ngLoadpageFinishedEvent) {
    	 $('#file-0a').fileinput({
    	        language: 'zh',
    	        uploadUrl: 'building/uploadFile.do?relateid='+$scope.document.id+"&tablename="+$scope.document.tablename,
    	    });

//
//         $('#file-0a').on('fileuploaded', function(event, data, previewId, index) {
//             var form = data.form, files = data.files, extra = data.extra,
//                 response = data.response, reader = data.reader;
//             alert('File uploaded triggered');
//         });
//
// //上传前
//         $('#file-0a').on('filepreupload', function(event, data, previewId, index) {
//             var form = data.form, files = data.files, extra = data.extra,
//                 response = data.response, reader = data.reader;
//             alert('File pre upload triggered');
//         });
    });
    
});