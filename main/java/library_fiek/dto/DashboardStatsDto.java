package library_fiek.dto;

public class DashboardStatsDto {
    private int totalBooks;
    private int totalMembers;
    private int activeLoans;
    private int overdueLoans;

    public DashboardStatsDto(int totalBooks, int totalMembers, int activeLoans, int overdueLoans) {
        this.totalBooks = totalBooks;
        this.totalMembers = totalMembers;
        this.activeLoans = activeLoans;
        this.overdueLoans = overdueLoans;
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public int getActiveLoans() {
        return activeLoans;
    }

    public int getOverdueLoans() {
        return overdueLoans;
    }
}
