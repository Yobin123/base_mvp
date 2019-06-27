# base_mvp
轻量型的mvp框架，既可以直接拷贝又可以直接依赖。
1. 进行mvp的封装，包含了basePresenter,baseActivity,baseFragment
2. 对presenter中view采用弱引用形式，同时处理了view销毁后,对presenter中视图对象的分离。
3. 增加了module到presenter的回调类
