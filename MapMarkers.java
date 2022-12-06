public enum MapMarkers {

    CIRCLE_MARK("-"), EMPTY_MARK("X");

    private final String marker;
    MapMarkers(String marker) {
        this.marker = marker;
    }

    public String getMarker() {
        return marker;
    }
}
