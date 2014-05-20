package com.mobicongo.app.tours.model;


/**
 * A simple struct containing information about a point of interest (POI).
 */
public class PointOfInterest {

    /**
     * Type of the point of interest.
     */
    public enum Type {
        MUSEUM(30),
        SHOPPING(210),
        LANDMARK(270);

        /** Hue of the marker to use for this type of POI. */
        final float mHue;

        private Type(float hue) {
            this.mHue = hue;
        }
        
    }

    /** Title of the POI. */
    public String mTitle;

    /** A short (1-2 sentence) description of the POI. */
    public String mDescription;

    /** General category to which this POI belongs. */
    public Type mType;

    /** The location of this POI. */
    //public LatLng mLocation;

    /** The Latitude and Longitude of this POI. */
    public double mLatitude;
    public double mLongitude;
    
    /** The URL to a thumbnail image of this POI. */
    public String mPictureUrl;

    /** A copyright attribution for the thumbnail image. */
    public String mPictureAttr;
    
    /** the id of the city. */
    public int mIdCity;
    
    /** A copyright attribution for the thumbnail image. */
    public String mAdresse;

    public PointOfInterest(String title,
            String description,
            Type type,
            String pictureAttr,
            String picture,
            double latitude,
            double longitude,
            int idcity,
            String adresse) {
        this.mTitle = title;
        this.mDescription = description;
        this.mType = type;
        this.mPictureAttr = pictureAttr;
        this.mPictureUrl = picture;
        this.mLatitude=latitude;
        this.mLongitude=longitude;
        //this.mLocation = location;
        this.mIdCity=idcity;
        this.mAdresse=adresse;
    }

    public PointOfInterest(){
    	//null constructor
    }
    
	public String getmTitle() {
		return mTitle;
	}
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}


	public String getmDescription() {
		return mDescription;
	}


	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}


	public Type getmType() {
		return mType;
	}


	public void setmType(Type mType) {
		this.mType = mType;
	}


	/*public LatLng getmLocation() {
		return mLocation;
	}


	public void setmLocation(LatLng mLocation) {
		this.mLocation = mLocation;
	}
*/

	public String getmPictureUrl() {
		return mPictureUrl;
	}


	public void setmPictureUrl(String mPictureUrl) {
		this.mPictureUrl = mPictureUrl;
	}


	public String getmPictureAttr() {
		return mPictureAttr;
	}


	public void setmPictureAttr(String mPictureAttr) {
		this.mPictureAttr = mPictureAttr;
	}


	public int getmIdCity() {
		return mIdCity;
	}


	public void setmIdCity(int mIdCity) {
		this.mIdCity = mIdCity;
	}

	public double getmLatitude() {
		return mLatitude;
	}

	public void setmLatitude(double mLatitude) {
		this.mLatitude = mLatitude;
	}

	public double getmLongitude() {
		return mLongitude;
	}

	public void setmLongitude(double mLongitude) {
		this.mLongitude = mLongitude;
	}

	public String getmAdresse() {
		return mAdresse;
	}

	public void setmAdresse(String mAdresse) {
		this.mAdresse = mAdresse;
	}

	
	
	
	
}
