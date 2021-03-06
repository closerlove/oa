package com.qf.oa.entity;

import java.io.Serializable;

public class Resc implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resc.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resc.pid
     *
     * @mbggenerated
     */
    private Integer pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resc.rname
     *
     * @mbggenerated
     */
    private String rname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resc.repath
     *
     * @mbggenerated
     */
    private String repath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resc.redesc
     *
     * @mbggenerated
     */
    private String redesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resc.restate
     *
     * @mbggenerated
     */
    private Integer restate;


    private String pname;

    private Boolean checked;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table resc
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resc.id
     *
     * @return the value of resc.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resc.id
     *
     * @param id the value for resc.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resc.pid
     *
     * @return the value of resc.pid
     *
     * @mbggenerated
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resc.pid
     *
     * @param pid the value for resc.pid
     *
     * @mbggenerated
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resc.rname
     *
     * @return the value of resc.rname
     *
     * @mbggenerated
     */
    public String getRname() {
        return rname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resc.rname
     *
     * @param rname the value for resc.rname
     *
     * @mbggenerated
     */
    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resc.repath
     *
     * @return the value of resc.repath
     *
     * @mbggenerated
     */
    public String getRepath() {
        return repath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resc.repath
     *
     * @param repath the value for resc.repath
     *
     * @mbggenerated
     */
    public void setRepath(String repath) {
        this.repath = repath == null ? null : repath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resc.redesc
     *
     * @return the value of resc.redesc
     *
     * @mbggenerated
     */
    public String getRedesc() {
        return redesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resc.redesc
     *
     * @param redesc the value for resc.redesc
     *
     * @mbggenerated
     */
    public void setRedesc(String redesc) {
        this.redesc = redesc == null ? null : redesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resc.restate
     *
     * @return the value of resc.restate
     *
     * @mbggenerated
     */
    public Integer getRestate() {
        return restate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resc.restate
     *
     * @param restate the value for resc.restate
     *
     * @mbggenerated
     */
    public void setRestate(Integer restate) {
        this.restate = restate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resc
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Resc other = (Resc) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getRname() == null ? other.getRname() == null : this.getRname().equals(other.getRname()))
            && (this.getRepath() == null ? other.getRepath() == null : this.getRepath().equals(other.getRepath()))
            && (this.getRedesc() == null ? other.getRedesc() == null : this.getRedesc().equals(other.getRedesc()))
            && (this.getRestate() == null ? other.getRestate() == null : this.getRestate().equals(other.getRestate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resc
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getRname() == null) ? 0 : getRname().hashCode());
        result = prime * result + ((getRepath() == null) ? 0 : getRepath().hashCode());
        result = prime * result + ((getRedesc() == null) ? 0 : getRedesc().hashCode());
        result = prime * result + ((getRestate() == null) ? 0 : getRestate().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resc
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", rname=").append(rname);
        sb.append(", repath=").append(repath);
        sb.append(", redesc=").append(redesc);
        sb.append(", restate=").append(restate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}