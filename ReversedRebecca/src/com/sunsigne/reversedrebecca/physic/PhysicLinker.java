package com.sunsigne.reversedrebecca.physic;

import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.physic.debug.ElevatorMode;
import com.sunsigne.reversedrebecca.physic.debug.FastWorldMode;
import com.sunsigne.reversedrebecca.physic.debug.MultiToolMode;
import com.sunsigne.reversedrebecca.physic.debug.SureCriticalMode;
import com.sunsigne.reversedrebecca.physic.debug.SwiftMovingMode;
import com.sunsigne.reversedrebecca.physic.debug.VisibleHitboxMode;
import com.sunsigne.reversedrebecca.physic.debug.WallPassMode;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraMovingLaw;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShakingLaw;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CollisionLaw;
import com.sunsigne.reversedrebecca.physic.natural.correlated.MoveTowardGoalLaw;
import com.sunsigne.reversedrebecca.physic.natural.correlated.PathFindingLaw;
import com.sunsigne.reversedrebecca.physic.natural.correlated.PlayerBlockingAvoiderLaw;
import com.sunsigne.reversedrebecca.physic.natural.correlated.RoundToTileLaw;
import com.sunsigne.reversedrebecca.physic.natural.correlated.VelocityLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.BlinkingRecoveringLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.ExplosionBrightnessLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.FadeMenuLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.FadePuzzleLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.HideHUDDuringMenuLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.HightlightAbovenessLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.IndependantLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.LifeAndDeathLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.MouseObjectRenderAboveLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.PlayerFinderLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.PsychopathActionRenderingLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.RefreshProcessorLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.SingleInteractivityLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.UpdateLayersLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.UpgradeChatTextQualityLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.WaitforLaw;

public class PhysicLinker {

	////////// PHYSICS ////////////

	private static final PhysicLaw C_VELOCITY = PhysicList.getList().getObject(new VelocityLaw());
	private static final PhysicLaw C_GOAL = PhysicList.getList().getObject(new MoveTowardGoalLaw());
	private static final PhysicLaw C_COLLISION = PhysicList.getList().getObject(new CollisionLaw());
	private static final PhysicLaw C_TILE = PhysicList.getList().getObject(new RoundToTileLaw());
	private static final PhysicLaw C_AVOIDER = PhysicList.getList().getObject(new PlayerBlockingAvoiderLaw());
	private static final PhysicLaw C_PATH = PhysicList.getList().getObject(new PathFindingLaw());
	private static final PhysicLaw C_CAMERA = PhysicList.getList().getObject(new CameraMovingLaw());
	private static final PhysicLaw C_SHAKE = PhysicList.getList().getObject(new CameraShakingLaw());

	private static final IndependantLaw I_BLINKING = new BlinkingRecoveringLaw().getIndependantLaw();
	private static final IndependantLaw I_EXPLOSION = new ExplosionBrightnessLaw().getIndependantLaw();
	private static final IndependantLaw I_FADE_MENU = new FadeMenuLaw().getIndependantLaw();
	private static final IndependantLaw I_FADE_PUZZLE = new FadePuzzleLaw().getIndependantLaw();
	private static final IndependantLaw I_HUD = new HideHUDDuringMenuLaw().getIndependantLaw();
	private static final IndependantLaw I_HIGHLIGHT = new HightlightAbovenessLaw().getIndependantLaw();
	private static final IndependantLaw I_DEATH = new LifeAndDeathLaw().getIndependantLaw();
	private static final IndependantLaw I_MOUSE = new MouseObjectRenderAboveLaw().getIndependantLaw();
	private static final IndependantLaw I_PLAYER = new PlayerFinderLaw().getIndependantLaw();
	private static final IndependantLaw I_PSYCHO = new PsychopathActionRenderingLaw().getIndependantLaw();
	private static final IndependantLaw I_HACK = new RefreshProcessorLaw().getIndependantLaw();
	private static final IndependantLaw I_INTERACT = new SingleInteractivityLaw().getIndependantLaw();
	private static final IndependantLaw I_LAYER = new UpdateLayersLaw().getIndependantLaw();
	private static final IndependantLaw I_CHAT = new UpgradeChatTextQualityLaw().getIndependantLaw();
	private static final IndependantLaw I_WAITFOR = new WaitforLaw().getIndependantLaw();

	@SuppressWarnings("unused")
	private static final DebugMode D_ELEVATOR = new ElevatorMode().getDebugMode();
	@SuppressWarnings("unused")
	private static final DebugMode D_FAST = new FastWorldMode().getDebugMode(); // already by default

	private static final DebugMode D_MULTI = new MultiToolMode().getDebugMode();
	private static final DebugMode D_CRIT = new SureCriticalMode().getDebugMode();
	private static final DebugMode D_SWIFT = new SwiftMovingMode().getDebugMode();
	private static final DebugMode D_HITBOX = new VisibleHitboxMode().getDebugMode();
	private static final DebugMode D_WALLPASS = new WallPassMode().getDebugMode();

	////////// LINKERS ////////////

	public static final PhysicLaw[] LIVING = { C_VELOCITY, C_GOAL, C_COLLISION, C_TILE, C_AVOIDER, C_PATH, C_SHAKE, I_BLINKING,
			I_HIGHLIGHT, I_DEATH, I_INTERACT, I_WAITFOR, D_SWIFT, D_HITBOX };
	public static final PhysicLaw[] PIRANHA = { C_COLLISION, C_SHAKE, I_HIGHLIGHT, I_INTERACT, I_WAITFOR, D_HITBOX };
	public static final PhysicLaw[] PLAYER = { C_VELOCITY, C_GOAL, C_COLLISION, C_TILE, C_PATH, C_CAMERA, C_SHAKE, I_BLINKING,
			I_FADE_PUZZLE, I_HIGHLIGHT, I_DEATH, I_MOUSE, I_PLAYER, I_WAITFOR, D_SWIFT, D_HITBOX, D_WALLPASS };
	public static final PhysicLaw[] WORLD = { C_SHAKE, I_BLINKING, I_EXPLOSION, I_FADE_MENU, I_PSYCHO, I_INTERACT, I_LAYER,
			D_MULTI, D_CRIT };
	public static final PhysicLaw[] GROUND = { C_SHAKE };
	public static final PhysicLaw[] LIGHT = { C_SHAKE, I_EXPLOSION };
	
	public static final PhysicLaw[] ANIMATION = { C_VELOCITY, C_SHAKE, I_EXPLOSION };
	public static final PhysicLaw[] BUBBLE = { C_SHAKE, I_INTERACT };
	public static final PhysicLaw[] CHAT = { I_CHAT };
	public static final PhysicLaw[] COLLISIONNER = { C_COLLISION, C_SHAKE, D_HITBOX };
	public static final PhysicLaw[] HUD = { I_HUD };
	public static final PhysicLaw[] MOVER = { C_VELOCITY, C_SHAKE };
	public static final PhysicLaw[] MENU = { I_FADE_MENU, D_HITBOX };
	public static final PhysicLaw[] PROJECTILE = { C_VELOCITY, C_COLLISION, C_SHAKE, D_HITBOX };
	public static final PhysicLaw[] PUZZLE = { C_SHAKE, I_FADE_PUZZLE, I_MOUSE, D_HITBOX };
	public static final PhysicLaw[] PUZZLE_HACK = { C_SHAKE, I_FADE_PUZZLE, I_MOUSE, I_HACK, D_HITBOX };
	public static final PhysicLaw[] PUZZLE_MOVER = { C_VELOCITY, C_SHAKE, I_FADE_PUZZLE, I_MOUSE, D_HITBOX };
	public static final PhysicLaw[] PUZZLE_COLLISION = { C_VELOCITY, C_COLLISION, C_SHAKE, I_FADE_PUZZLE, D_HITBOX };
	public static final PhysicLaw[] PUZZLER = { C_COLLISION, C_SHAKE, I_HIGHLIGHT, I_INTERACT, D_HITBOX };
	public static final PhysicLaw[] PSYCHO = { I_PSYCHO };

}
