package jobj.vertex;

/**
 * <h1> Vertex </h1>
 * This class is used to store all four vertex types. Those are:
 * <ul> "v" Vertex </ul>
 * <ul> "vn" Vertex Normals </ul>
 * <ul> "vt" Vertex Texture </ul>
 * <ul> "vp" Vertex Space Parameters</ul>
 * @author Alexander Brennecke
 *
 */
public class Vertex {
	private Double xCoordinate;
	private Double yCoordinate;
	private Double zCoordinate;
	
	/**
	 * Adds the coordinates to the vertex.
	 * @param x the x Coordinate
	 * @param y	the y Coordinate
	 * @param z the z Coordinate (could be empty in "vt" verticies)
	 */
	public Vertex(Double x, Double y, Double z){
		xCoordinate = x;
		yCoordinate = y;
		zCoordinate = z;
	}

	public Double getxCoordinate() {
		return xCoordinate;
	}

	public Double getyCoordinate() {
		return yCoordinate;
	}

	public Double getzCoordinate() {
		return zCoordinate;
	}
}
