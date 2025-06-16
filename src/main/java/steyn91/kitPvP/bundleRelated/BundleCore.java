package steyn91.kitPvP.bundleRelated;

public class BundleCore {

    public final int maxHealth;
    public final int currentHealth;
    public final int currentResource;
    public final int maxResource;
    // TODO остальные характеристики для перса

    public BundleCore(int maxHealth, int currentHealth, int currentResource, int maxResource) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.currentResource = currentResource;
        this.maxResource = maxResource;
    }

}
