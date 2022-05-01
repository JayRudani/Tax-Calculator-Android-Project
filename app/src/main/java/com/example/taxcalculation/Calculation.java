package com.example.taxcalculation;

public class Calculation {

    double[] values = {};

    public double calculateRRSPContribution(double rrspLimit, float rrspPer){
        return (rrspLimit*(rrspPer/100));
    }

    public double calculateTaxableIncome(double income, double contribution){
        return (income-contribution);
    }

    public double calculateTotalTax(double income){
        if(income <= 46226){
            return firstCategory(income);
        }else if(income > 46226 && income <= 50197){
            return secondCategory(income);
        }else if(income > 50197 && income <= 81411){
            return thirdCategory(income);
        }else if(income > 81411 && income <= 92454){
            return fourthCategory(income);
        }else if(income > 92454 && income <= 95906){
            return fifthCategory(income);
        }else if(income > 95906 && income <= 100392){
            return sixthCategory(income);
        }else if(income > 100392 && income <= 150000){
            return seventhCategory(income);
        }else if(income > 150000 && income <= 155625){
            return eightCategory(income);
        }else if(income > 155625 && income <= 220000){
            return ninthCategory(income);
        }else if(income > 220000 && income <= 221708){
            return tenthCategory(income);
        }
        else
            return eleventhCategory(income);
    }

    private double firstCategory(double income){
        return (income*0.2005);
    }
    private double secondCategory(double income){
        return ((46226*0.2005)+((income-46226)*0.2415));
    }
    private double thirdCategory(double income){
        return ((46226*0.2005)+(3971*0.2415)+((income-50197)*0.2965));
    }
    private double fourthCategory(double income){
        return ((46226*0.2005)+(3971*0.2415)+(31214*0.2965)+((income-81411)*0.3148));
    }
    private double fifthCategory(double income){
        return ((46226*0.2005)+(3971*0.2415)+(31214*0.2965)+(11043*0.3148)+((income-92454)*0.3389));
    }
    private double sixthCategory(double income){
        return ((46226*0.2005)+(3971*0.2415)+(31214*0.2965)+(11043*0.3148)+(3452*0.3389)+((income-95906)*0.3791));
    }
    private double seventhCategory(double income){
        return ((46226*0.2005)+(3971*0.2415)+(31214*0.2965)+(11043*0.3148)+(3452*0.3389)+(4486*0.3791)+((income-100392)*0.4341));
    }
    private double eightCategory(double income){
        return ((46226*0.2005)+(3971*0.2415)+(31214*0.2965)+(11043*0.3148)+(3452*0.3389)+(4486*0.3791)+(49608*0.4341)+((income-150000)*0.4497));
    }
    private double ninthCategory(double income){
        return ((46226*0.2005)+(3971*0.2415)+(31214*0.2965)+(11043*0.3148)+(3452*0.3389)+(4486*0.3791)+(49608*0.4341)+(5625*0.4497)+((income-155625)*0.4835));
    }
    private double tenthCategory(double income){
        return ((46226*0.2005)+(3971*0.2415)+(31214*0.2965)+(11043*0.3148)+(3452*0.3389)+(4486*0.3791)+(49608*0.4341)+(5625*0.4497)+(64375*0.4835)+((income-220000)*0.4991));
    }
    private double eleventhCategory(double income){
        return ((46226*0.2005)+(3971*0.2415)+(31214*0.2965)+(11043*0.3148)+(3452*0.3389)+(4486*0.3791)+(49608*0.4341)+(5625*0.4497)+(64375*0.4835)+(1708*0.4991)+((income-221708)*0.5353));
    }
}
