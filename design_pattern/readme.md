# 设计模式(Design Pattern)
## 1. 策略模式 Strategy Pattern
    策略模式：实现算法族，分别封装起来，让他们之间可以相互替换，此模式让
    算法的变化独立于使用算法的客户

## 2. 观察者模式 Observer Pattern
    观察者模式：在对象之间定义一对多的起来，这样一来，当一个对象改变
    状态时，依赖他的对象都会收到通知。

## 3. 装饰模式 Decorator Pattern
    装饰者模式:动态地将责任附加到对象上，想要扩展功能，装饰者提供
    有别于继承的另一种实现。
    
## 4. 单例模式 Singleton Pattern
    单例模式： 确保一个类最多只有一个实例，并提供全局
    访问方式，永远只有一个对象（线程池，缓存，硬件设备）
    
## 5. 工厂模式 Factory Pattern
    工厂模式： 
        1. 简单工厂模式 Simple Factory Pattern
            将对象的创建交由特定的类来创建
        2. 工厂方法模式 Factory Method Pattern
            定义一个创建对象的抽象方法，由子类决定要实例化的类，工厂方法模式将对象的实例化推迟到了子类。
        3. 抽象工厂模式 Abstract Factory Pattern
            定义了一个接口用于创建相关或有依赖关系的对象族，而无须明确指定具体类。
            
## 6. 命令模式 Command Pattern
    将请求，命令，动作等封装成对象，这样可以让项目使用这些对象来参数化其他
    对象，使得命令的请求者和执行者解耦。
    
## 7. 适配器模式	Adapter Pattern
    将一个类的接口转换成另一种接口，让原本接口不兼容的类可以兼容

## 8. 门面模式/外观模式	Facade Pattern
    提供一个统一的接口，来访问子系统中的一群功能相关的接口，外观模式定义了一个高层接口，让子类调用更方便。
    提供了一个统一的接口，用来访问子系统中的一群接口，外观定义了一个高层接口，
    让子类更容易使用。

## 9. 模板方法模式	Template Method Pattern
    封装了一个算法步骤，并允许子类为一个或多个步骤提供具体实现。
    模板模式可以使子类不改变算法结构的情况下，重新定义算法中的某些步骤。
    
## 10. 迭代器模式	Iterator Pattern
     提供一种方法可以顺序方位一个聚合对象中的各种对象。
     
## 11. 组合模式 Composite Pattern

## 12. 状态模式	State Pattern
    允许对象在内部状态改变时更变它的行为，对象看起来好像修改了塔的类。

## 13. 代理模式	Proxy pattern

## 14. 享元模式	Flyweight Pattern

## 15. 桥梁模式/桥接模式	Bridge Pattern

## 16. 建造者模式/生成器模式	Builder Pattern

## 17. 责任链模式	Chain of Responsibility Pattern

## 18. 

## 19. 解释器模式	Interpreter Pattern

## 20. 中介者模式	Mediator Pattern

## 21. 备忘录模式	Memento Pattern

## 22. 原型模式	Prototype Pattern

## 23. 访问者模式	Visitor Pattern