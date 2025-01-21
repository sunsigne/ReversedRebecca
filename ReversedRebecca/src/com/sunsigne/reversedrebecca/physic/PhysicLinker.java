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

	private static final PhysicLaw C_VELOCITY = new VelocityLaw();
	private static final PhysicLaw C_GOAL = new MoveTowardGoalLaw();
	private static final PhysicLaw C_COLLISION = new CollisionLaw();
	private static final PhysicLaw C_TILE = new RoundToTileLaw();
	private static final PhysicLaw C_AVOIDER = new PlayerBlockingAvoiderLaw();
	private static final PhysicLaw C_PATH = new PathFindingLaw();
	private static final PhysicLaw C_CAMERA = new CameraMovingLaw();

	private static final IndependantLaw I_BLINKING = new BlinkingRecoveringLaw();
	private static final IndependantLaw I_EXPLOSION = new ExplosionBrightnessLaw();
	private static final IndependantLaw I_FADE_MENU = new FadeMenuLaw();
	private static final IndependantLaw I_FADE_PUZZLE = new FadePuzzleLaw();
	private static final IndependantLaw I_HUD = new HideHUDDuringMenuLaw();
	private static final IndependantLaw I_HIGHLIGHT = new HightlightAbovenessLaw();
	private static final IndependantLaw I_DEATH = new LifeAndDeathLaw();
	private static final IndependantLaw I_MOUSE = new MouseObjectRenderAboveLaw();
	private static final IndependantLaw I_PLAYER = new PlayerFinderLaw();
	private static final IndependantLaw I_PSYCHO = new PsychopathActionRenderingLaw();
	private static final IndependantLaw I_HACK = new RefreshProcessorLaw();
	private static final IndependantLaw I_INTERACT = new SingleInteractivityLaw();
	private static final IndependantLaw I_LAYER = new UpdateLayersLaw();
	private static final IndependantLaw I_CHAT = new UpgradeChatTextQualityLaw();
	private static final IndependantLaw I_WAITFOR = new WaitforLaw();

	@SuppressWarnings("unused")
	private static final DebugMode D_ELEVATOR = new ElevatorMode();
	@SuppressWarnings("unused")
	private static final DebugMode D_FAST = new FastWorldMode(); // already by default
	
	private static final DebugMode D_MULTI = new MultiToolMode();
	private static final DebugMode D_CRIT = new SureCriticalMode();
	private static final DebugMode D_SWIFT = new SwiftMovingMode();
	private static final DebugMode D_HITBOX = new VisibleHitboxMode();
	private static final DebugMode D_WALLPASS = new WallPassMode();

	////////// LINKERS ////////////

	public static final PhysicLaw[] LIVING = { C_VELOCITY, C_GOAL, C_COLLISION, C_TILE, C_AVOIDER, C_PATH, I_BLINKING,
			I_HIGHLIGHT, I_DEATH, I_INTERACT, I_WAITFOR, D_SWIFT, D_HITBOX };
	public static final PhysicLaw[] PIRANHA = { C_COLLISION, I_HIGHLIGHT, I_INTERACT, I_WAITFOR, D_HITBOX };
	public static final PhysicLaw[] PLAYER = { C_VELOCITY, C_GOAL, C_COLLISION, C_TILE, C_PATH, C_CAMERA, I_BLINKING,
			I_FADE_PUZZLE, I_HIGHLIGHT, I_DEATH, I_MOUSE, I_PLAYER, I_WAITFOR, D_SWIFT, D_HITBOX, D_WALLPASS };
	public static final PhysicLaw[] WORLD = { I_BLINKING, I_EXPLOSION, I_FADE_MENU, I_PSYCHO, I_INTERACT, I_LAYER,
			D_MULTI, D_CRIT };

	public static final PhysicLaw[] ANIMATION = { C_VELOCITY, I_EXPLOSION };
	public static final PhysicLaw[] BUBBLE = { I_INTERACT };
	public static final PhysicLaw[] CHAT = { I_CHAT };
	public static final PhysicLaw[] COLLISIONNER = { C_COLLISION, D_HITBOX };
	public static final PhysicLaw[] EXPLOSION = { I_EXPLOSION };
	public static final PhysicLaw[] HUD = { I_HUD };
	public static final PhysicLaw[] MOVER = { C_VELOCITY/* , C_TILE, D_SWIFT */ };
	public static final PhysicLaw[] MENU = { I_FADE_MENU, D_HITBOX };
	public static final PhysicLaw[] OPTIMIZED_WALL = { D_HITBOX };
	public static final PhysicLaw[] PROJECTILE = { C_VELOCITY, C_COLLISION, D_HITBOX };
	public static final PhysicLaw[] PUZZLE = { I_FADE_PUZZLE, I_MOUSE, D_HITBOX };
	public static final PhysicLaw[] PUZZLE_HACK = { I_FADE_PUZZLE, I_MOUSE, I_HACK, D_HITBOX };
	public static final PhysicLaw[] PUZZLE_MOVER = { C_VELOCITY, I_FADE_PUZZLE, I_MOUSE, D_HITBOX };
	public static final PhysicLaw[] PUZZLE_COLLISION = { C_VELOCITY, C_COLLISION, I_FADE_PUZZLE, D_HITBOX };
	public static final PhysicLaw[] PUZZLER = { C_COLLISION, I_HIGHLIGHT, I_INTERACT, D_HITBOX };
	public static final PhysicLaw[] PSYCHO = { I_PSYCHO };

}
