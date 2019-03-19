package com.nightonke.blurlockview.Eases;

public enum EaseType {
    EaseInSine(EaseInSine.class),
    EaseOutSine(EaseOutSine.class),
    EaseInOutSine(EaseInOutSine.class),
    EaseInQuad(EaseInQuad.class),
    EaseOutQuad(EaseOutQuad.class),
    EaseInOutQuad(EaseInOutQuad.class),
    EaseInCubic(EaseInCubic.class),
    EaseOutCubic(EaseOutCubic.class),
    EaseInOutCubic(EaseInOutCubic.class),
    EaseInQuart(EaseInQuart.class),
    EaseOutQuart(EaseOutQuart.class),
    EaseInOutQuart(EaseInOutQuart.class),
    EaseInQuint(EaseInQuint.class),
    EaseOutQuint(EaseOutQuint.class),
    EaseInOutQuint(EaseInOutQuint.class),
    EaseInExpo(EaseInExpo.class),
    EaseOutExpo(EaseOutExpo.class),
    EaseInOutExpo(EaseInOutExpo.class),
    EaseInCirc(EaseInCirc.class),
    EaseOutCirc(EaseOutCirc.class),
    EaseInOutCirc(EaseInOutCirc.class),
    EaseInBack(EaseInBack.class),
    EaseOutBack(EaseOutBack.class),
    EaseInOutBack(EaseInOutBack.class),
    EaseInElastic(EaseInElastic.class),
    EaseOutElastic(EaseOutElastic.class),
    EaseInOutElastic(EaseInOutElastic.class),
    EaseInBounce(EaseInBounce.class),
    EaseOutBounce(EaseOutBounce.class),
    EaseInOutBounce(EaseInOutBounce.class),
    Linear(Linear.class);
    
    private Class easingType;

    private EaseType(Class cls) {
        this.easingType = cls;
    }

    public float getOffset(float f) {
        try {
            return ((CubicBezier) this.easingType.getConstructor(new Class[0]).newInstance(new Object[0])).getOffset(f);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error("CubicBezier init error.");
        }
    }
}
