let vm = new Vue({
    el:'#main-container',
    data:{
        appList:[],
        pageInfo:{
            pageSize:5
        },
        app:{},
        active:true
    },
    methods:{
        selectPage:function (pageNum,pageSize) {
            axios({
                url:`manager/app/selectPage/${pageNum}/${pageSize}`
            }).then( response => {
                //箭头函数省略了function关键字  且自动传入this为上一作用域中的this(vue)
                // console.log(this);
                //data对应java的Result对象  {obj:返回的封装对象,msg:消息,success:状态}
                //obj:{list:{....},pageNum:xx,pageSize:xxx}
                // console.log(response.data);
                this.appList=response.data.obj.list;
                this.pageInfo = response.data.obj;
            }).catch(error =>{
                console.log(error);
            })
        },
        toUpdate:function(app){
            //1.数据传递到子窗口
            layer.obj=app;//绑定数据到父窗口index的layer变量上
            //2.通过layer实现弹出一个子窗口(基于iframe标签实现)，将app对象传递到子窗口
            layer.open({
                type: 2,//设置弹出类型为动态加载一个iframe子窗口
                title: '更新',//子窗口标题
                area: ['60%', '60%'],//设置子窗口的宽高  可以支持px和占据父窗口比例
                content: 'manager/app/toUpdate', //iframe的url
                end:() => {  //当子窗口关闭后回调
                    // layer.msg("子窗口关闭...")
                    if(!layer.success){//子窗口操作失败，刷新页面
                        this.selectPage(1,this.pageInfo.pageSize);
                    }
                }
            })
        },
        toDelete:function (id) {
            //删除
            let d_this = this;
            layer.msg('你确定删除么？', {
                time: 0 //不自动关闭
                , btn: ['是', '否']
                , yes: () => {
                    //选择是则发送删除请求
                    axios({
                        url: `manager/app/toDelete/${id}`
                    }).then(response => {
                        if (response.data.success) {
                            d_this.selectPage(1, d_this.pageInfo.pageSize);
                        }
                        layer.msg(response.data.msg)
                    }).catch((error) => {
                        console.log(error);
                    })
                }
            })
        },
        insert:function(){
            axios({
                url:'manager/app/insert',
                method:'post',
                data:this.app/*{}模拟失败数据*/
            }).then(response=>{

                if(response.data.success){
                    //保存成功后 将   动态切换选项卡和列表内容
                    this.active=true;
                    this.selectPage(1,this.pageInfo.pageSize);//刷新数据
                    //清空新增表单中的数据
                    this.app={};
                }
                layer.msg(response.data.msg);

            }).catch(error=>{//后台抛出错误无法被ExceptionController捕获处理的时候进入
                layer.msg(error.message);
            })
        },
        changeActive:function(active){
            this.active=active;   //点击选项卡修改绑定的active值
        }

    },
    created:function () {
        // this.findAll();
        this.selectPage(1,this.pageInfo.pageSize);
    }
})