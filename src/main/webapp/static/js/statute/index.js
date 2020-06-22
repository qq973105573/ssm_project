let vm = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 5
        },//接受后台页对象
        type:'',
        obj:{},
        active:true,
        statute:{},
        myConfig:{
            UEDITOR_HOME_URL:"static/ueditor/",
            serverUrl:'ueditor',
            maximumWords:500000
        }
    },
    components:{
        VueUeditorWrap
    },
    methods: {
        selectAll:function () {
            this.type='',
            this.selectPage(1,this.pageInfo.pageSize);
        },
        selectPage: function (pageNum,pageSize) {
            axios({
                url:`manager/statute/selectPage/${pageNum}/${pageSize}`,
                params:{type:this.type},
            }).then(response=>{
                // console.log(response);
                this.pageInfo=response.data.obj;
            }).catch(error=>{
                layer.msg(error.message);
            })
        },
        toUpdate: function (obj) {
            layer.obj = obj;
            layer.open({
                type: 2,    //弹出  frame 窗口
                // title: '更新app',
                area: ['80%', '80%'],  //宽,高   比例是占据父窗口的比例
                content: 'manager/statute/toUpdate',   //请求网页地址
                end: function () {//关闭弹出的子窗口后自动回调
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
                        url: `manager/statute/toDelete/${id}`
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
                url:'manager/statute/insert',
                method:'post',
                data:this.obj/*{}模拟失败数据*/
            }).then(response=>{
                if(response.data.success){
                    //保存成功后 将   动态切换选项卡和列表内容
                    this.active=true;
                    this.selectPage(1,this.pageInfo.pageSize);//刷新数据
                    //清空新增表单中的数据
                    this.obj={};
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
    created: function () {
        this.selectPage(1, this.pageInfo.pageSize);
    },

});