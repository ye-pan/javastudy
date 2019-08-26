# zookeeper学习

## 分布式架构

CAP：一个分布式系统不可能同时满足一致性，可用性，分区容错性，最多只能同时满足其中两项

BASE：即使无法做到强一致性，但每个应用都可以根据自身的业务特点，采用适当的方式来使系统达到最终一致性

## 一致性协议

2PC，二段提交：
1. 提交事务请求，
2. 执行事务提交

优点：原理简单，实现方便
缺点：同步阻塞，单点问题，脑裂，太过保守

3PC，三段提交：
1. CanCommit
2. PreCommit
3. DoCommit

Paxos算法：

## zookeeper 与 paxos

zookeeper是一个典型的分布式数据一致性的解决方案，分布式程序可以基于它实现诸如数据发布/订阅，负载均衡，
命名服务，分布式协调/通知，集群管理，master选举，分布式锁和分布式队列等。zookeeper可以保证下面的分布式
一致性特性：
* 顺序一致性，同一客户端发起的事务请求，最终将会严格地按照其发起顺序被应用到zookeeper中去
* 原子性，所有事务请求的处理结果在整个集群中所有机器上的应用情况是一致的
* 单一视图，无论客户端连接的是哪个zookeeper服务，其看到的数据模型都是一致的
* 可靠性，一旦服务端成功的应用了一个事务，并完成对客户端的响应，那么该事务所引起的服务端状态变更将会一直
被保留下来
* 实时性，zookeeper仅仅保证在一定时间段内，客户端最终一定能够从服务端读取到最新的数据状态

zookeeper的基本概念：
* 集群角色，leader follower observer三种角色。leader通过leader选举过程产生，leader提供了读/写服务，
follower和observer只提供读服务，observer不参与leader选举过程，也不参与写操作的过半写成功策略，用作
在不影响写性能的情况下提升集群的读性能。
* 会话
* 数据节点
* 版本
* Watcher，事件监听，允许用户在指定节点上注册一些watcher，并且在一些特定事件触发的时候，zookeeper
服务端会将事件通知到感兴趣的客户端上。
* ACL

## 使用zookeeper

创建节点，CreateMode：
* PERSISTENT，持久
* PERSISTENT_SEQUENTIAL，持久顺序
* EPHEMERAL，临时
* EPHEMERAL_SEQUENTIAL，临时顺序


## curator

基于curator-framework的recipes的源代码构建一些基于zookeeper的demo，主要用于zookeeper应用及curator客户端
API学习。