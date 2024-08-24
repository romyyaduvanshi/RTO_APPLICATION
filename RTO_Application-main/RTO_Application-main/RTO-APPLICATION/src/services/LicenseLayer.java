package services;

public interface LicenseLayer {
    public void applyForLicense(int userId);
    public void checkLicenseStatus();
    public void viewPendingLicenses();
    public void approveLicense();
}
