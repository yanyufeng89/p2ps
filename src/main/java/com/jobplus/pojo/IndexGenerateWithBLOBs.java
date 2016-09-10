package com.jobplus.pojo;

public class IndexGenerateWithBLOBs extends IndexGenerate {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6433277361818491579L;

	private String indexcont9;

    private String indexcont10;

    public String getIndexcont9() {
        return indexcont9;
    }

    public void setIndexcont9(String indexcont9) {
        this.indexcont9 = indexcont9 == null ? null : indexcont9.trim();
    }

    public String getIndexcont10() {
        return indexcont10;
    }

    public void setIndexcont10(String indexcont10) {
        this.indexcont10 = indexcont10 == null ? null : indexcont10.trim();
    }
}