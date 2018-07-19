<template>
	<div class="" style="height:100%;overflow: visible;text-align:center">
	    <div class="" style="height:100%">
	        <div  class="col-md-12 t-middle" style="height:100%;vertical-align: -webkit-baseline-middle;">

							 <!-- <iframe width="100%;" height='90%'  frameborder=0  scrolling=auto src="http://localhost:8080/activiti-app/editor/"></iframe> -->
							 <iframe style="height:100%;width: 100%;" frameborder=0  scrolling=auto src="http://106.14.172.38:8990/activiti-app/editor/"></iframe>

	        </div>
		 </div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			nodes: this.$router.options.routes
		}
	},
	created() {
		//这里没有直接使用this.$router.options.routes，是因为addRoute的路由规则，在这里this.$router.options.routes获取不到
		//有兴趣的可以看一下源码，是为什么获取不到，但是却又有规则了
		//另外在开发的时候，可能由于是热部署，也会不断重复的给nodes添加元素，造成导航条有重复的，简单解决，可以设置一个开关
		let isLoadNodes = sessionStorage.getItem('isLoadNodes')
		if (!isLoadNodes) {
			let data = JSON.parse(window.sessionStorage.getItem('user'))
			this.nodes.push(...data)
			console.log(this.nodes)
			sessionStorage.setItem('isLoadNodes', 'true')
		}
	},
	methods: {
		onSubmit() {
			console.log('submit!');
		},
		handleopen() {
			//console.log('handleopen');
		},
		handleclose() {
			//console.log('handleclose');
		},
		handleselect: function(a, b) {
		}
	},
	mounted() {
		console.log( this.$router.options.routes)

	},
	components: {
	}
}

</script>

<style scoped lang="scss">
.container {
	position: absolute;
	top: 0px;
	bottom: 0px;
	width: 100%;
	.header {
		height: 60px;
		line-height: 60px;
		background: #1F2D3D;
		color: #c0ccda;
		.userinfo {
			text-align: right;
			padding-right: 35px;
			.userinfo-inner {
				color: #c0ccda;
				cursor: pointer;
				img {
					width: 40px;
					height: 40px;
					border-radius: 20px;
					margin: 10px 0px 10px 10px;
					float: right;
				}
			}
		}
		.logo {
			font-size: 22px;
			img {
				width: 40px;
				float: left;
				margin: 10px 10px 10px 18px;
			}
			.txt {
				color: #20a0ff
			}
		}
	}
	.main {
		background: #324057;
		position: absolute;
		top: 60px;
		bottom: 0px;
		overflow: hidden;
		aside {
			width: 230px;
		}
		.content-container {
			background: #f1f2f7;
			position: absolute;
			right: 0px;
			top: 0px;
			bottom: 0px;
			left: 230px;
			overflow-y: scroll;
			padding: 20px;
			.breadcrumb-container {
				margin-bottom: 15px;
				.title {
					width: 200px;
					float: left;
					color: #475669;
				}
				.breadcrumb-inner {
					float: right;
				}
			}
			.content-wrapper {
				background-color: #fff;
				box-sizing: border-box;
			}
		}
	}
}
</style>
