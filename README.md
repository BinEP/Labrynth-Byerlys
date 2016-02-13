# Labrynth-Byerlys

Variables to use
controller - add BSShapes to this. <br>
Only add shapes once. They always get drawn.

List of BSShapes <br>
- BSCircle
- BSCompound
- BSEllipse
- BSLine
- BSLine
- BSPolygon
- BSString

Methods and variables to use for Shapes. Plus anything inside of the shapes

'''
	
	public int deltaX = 0;
	public int deltaY = 0;
	private Direction direction = Direction.none;
	private Color color = Color.WHITE;
	private boolean fill = true;
	
	public int x;
	public int y;
		
	
	@Override
	public void setColor(Color c) {
		color = c;
	}

	@Override
	public Color getColor() {
		return color;
	}
	
	@Override
	public void setFilled(boolean filled) {
		fill = filled;		
	}

	@Override
	public boolean filledShape() {
		return fill;
	}

	public void autoDraw(Graphics2D g) {
		if (fill) fill(g); 
		else draw(g);
	}
	
	@Override
	public void setMovement(int dx, int dy) {
		deltaX = dx;
		deltaY = dy;
		
		if (dx != 0) {
			direction = (dx > 0) ? Direction.right : Direction.left;
		} else {
			direction = (dy > 0) ? Direction.down : Direction.up;
		}
	}
	
	@Override
	public BSRectangle getPosition() {
		return new BSRectangle(getShape().getBounds());
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void setDirection(Direction d) {
		direction = d;
	}
	
	@Override
	public void update() {
		x += deltaX;
		y += deltaY;
	}

	@Override
	public int getDeltaX() {
		return deltaX;
	}

	@Override
	public int getDeltaY() {
		return deltaY;
	}
	'''
	
	
