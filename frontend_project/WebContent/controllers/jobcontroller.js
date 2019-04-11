/**
 * 
 */
app.controller('JobController', function($scope, JobService, $location,
		$rootScope,$routeParams) {
	$scope.user={"jobTitle":"developer","jobDescription":"job seeker","skillsRequired":"html","location":"lucknow","yrsOfExp":"2018","salary":"10000"," postedOn":"niit"}
	console.log('in job controller');
	var id=$routeParams.id
	//#/getjob/:id 
	//C to V
	if(id!=undefined){//this statement will get executed only for getjob/:id, getjobById()
		//this statement will not get executed for #/getalljobs
		JobService.getJob(id).then(function(response){
			$scope.job=response.data // select * from job_table where id=?
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	//C to V
	JobService.getAllJobs().then(function(response){
		$scope.jobs=response.data // Array of Jobs in JSON fmt
		alert('in get all jobs')
	},function(response){
		$rootScope.error=response.data //ErrorClazz
		$location.path('/login')
	})
	
	//V to C
	$scope.addJob = function() {
		// in view ng-model="job.jobTitle", ng-model="job.jobDesription"...
		console.log($scope.job)
		JobService.addJob($scope.job).then(function(response) {
			alert('Job details posted successfully')
			$location.path('/getalljobs')
		}, function(response) {// 401 - unauthorized access,401- Access
								// denied,500 - internal server error
			$rootScope.error = response.data;
			// for status code 401 , error message will get displayed in
			// login.html
			if (response.status == 401) {
				if (response.data.code == 5)// Authenticated but not
											// authorized,Access denied
				{
					alert("You are not authorized to add job details")
					$location.path('/home')
				} else {
					$location.path('/login')// Not authenticated
				}
			}
			// for status code 500, error message will get displayed in
			// jobform.html
		})
	}
})
