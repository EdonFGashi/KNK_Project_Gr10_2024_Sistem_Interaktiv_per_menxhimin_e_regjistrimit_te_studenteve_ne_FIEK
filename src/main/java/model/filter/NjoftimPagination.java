package model.filter;

public class NjoftimPagination implements Filter{
    private int page;
    private int size;

    public NjoftimPagination(int page, int size) {
        this.page = page;
        this.size = size;
    }

    @Override
    public String buildQuery() {
        String query = "";
        if(this.page >= 0){
            int limit = this.size;
            query+="LIMIT "+limit+" OFFSET "+this.page+";";
        }

        return query;
    }
}
