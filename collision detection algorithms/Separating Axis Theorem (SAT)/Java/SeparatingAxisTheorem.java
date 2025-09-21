import bean.CoordinationBean;
import bean.Projection;

public class SeparatingAxisTheorem {

    /**
     * checks if there is a collision between two convex shapes
     * convex shape means that any line drawn through the shape is only able to cross the shape twice
     * <a href="https://mathworld.wolfram.com/ConvexPolygon.html">MathWorld’s definition</a>
     */
    public boolean isCollision(CoordinationBean[] firstConvexShape, CoordinationBean[] secondConvexShape) {

        // Calculate the perpendicular vectors (normals) for all edges of both convex shapes.
        CoordinationBean[] firstShapePerpendicularVectors = getPerpendicularVectors(firstConvexShape);
        CoordinationBean[] secondShapePerpendicularVectors = getPerpendicularVectors(secondConvexShape);

        // if any vector of the shape is not overlapping, the shapes are not colliding
        if (!isVectorOverlapping(firstConvexShape, secondConvexShape, firstShapePerpendicularVectors)) {
            return false;
        }

        return !isVectorOverlapping(secondConvexShape, firstConvexShape, secondShapePerpendicularVectors);
    }

    private boolean isVectorOverlapping(CoordinationBean[] firstConvexShape, CoordinationBean[] secondConvexShape, CoordinationBean[] firstShapePerpendicularVectors) {
        for (CoordinationBean axis : firstShapePerpendicularVectors) {

            Projection p1 = project(axis, firstConvexShape);
            Projection p2 = project(axis, secondConvexShape);

            if (!overlapping(p1, p2)) {
                return false;
            }
        }
        return true;
    }

    private boolean overlapping(Projection p1, Projection p2) {
        return !(p1.max() < p2.min() || p2.max() < p1.min());
    }

    private static CoordinationBean[] getPerpendicularVectors(CoordinationBean[] shape) {

        CoordinationBean[] axes = new CoordinationBean[shape.length];

        for (int i = 0; i < shape.length; i++) {
            CoordinationBean firstVertex = shape[i];
            CoordinationBean secondVertex = shape[i + 1 < shape.length ? i + 1 : 0];
            CoordinationBean subtractedEdge = new CoordinationBean(firstVertex.x() - secondVertex.x(), firstVertex.y() - secondVertex.y());
            double length = Math.sqrt(subtractedEdge.x() * subtractedEdge.x() + subtractedEdge.y() * subtractedEdge.y());
            CoordinationBean normal = new CoordinationBean(
                    -subtractedEdge.y() / length,
                    subtractedEdge.x() / length
            );
            axes[i] = normal;
        }
        return axes;
    }

    private Projection project(CoordinationBean axis, CoordinationBean[] shape) {

        double min = dot(axis.x(), axis.y(), shape[0].x(), shape[0].y());
        double max = min;

        for (int i = 1; i < shape.length; i++) {
            double p = dot(axis.x(), axis.y(), shape[i].x(), shape[i].y());
            if (p < min) {
                min = p;
            } else if (p > max) {
                max = p;
            }
        }
        return new Projection(min, max);
    }

    private double dot(double x1, double y1, double x2, double y2) {
        return x1 * x2 + y1 * y2;
    }
}
