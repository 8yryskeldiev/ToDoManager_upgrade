package com.geektech.todomanager;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    public String title;
    public String description;
    public Date startDate;
    public Date deadline;
    public boolean isDone;
}
