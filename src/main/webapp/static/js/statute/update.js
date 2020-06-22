let vm = new Vue({
    el: '#main-container',
    data: {
        statute:{},
        myConfig:{//自定义VueUeditorWrap配置项
            // ueditor.config.js->VueUeditorWrap 覆盖为"/static/UEditor/"  -> myConfig覆盖为 /应用名/static/ueditor/
            UEDITOR_HOME_URL:"static/ueditor/",  //前端默认起始路径
            serverUrl:'ueditor',     //服务器统一请求接口
            maximumWords:500000
        }
    },
    methods: {
        doUpdate:function () {

            axios({
                url:'manager/statute/doUpdate',
                data:this.statute,
                method:'put'
            }).then(response => {
                let flag = false;
                /*
                成功则关闭当前页面窗，并提示
                失败则不关闭页面窗，直接提示，
                在用户关闭窗口的行为中绑定一个查询列表
                 */
                if(response.data.success){
                    //关闭当前窗口需要先获取到当前窗口索引值
                    let index = parent.layer.getFrameIndex(window.name);

                    //绑定成功标记
                    flag = true;
                    parent.layer.flag = flag;
                    // 再通过父窗口layer关闭当前窗口
                    parent.layer.close(index);
                    //在父窗口中提示
                    parent.layer.msg(response.data.msg);
                }else{
                    layer.msg(response.data.msg);
                    flag = false;
                    parent.layer.flag = flag;
                }

            }).catch(error =>{
                layer.msg(error.message);
            })
        },
        //取消按钮逻辑
        toClose:function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

    },
    created: function () {
        //初始化app  从父窗口的layer对象中获取obj属性
        this.statute = parent.layer.obj;
    },
    /*vue组件属性，用于引入一些已经封装好的vue组件对象*/
    components:{
        VueUeditorWrap
    }
});