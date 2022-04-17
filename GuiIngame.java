//Just put these methods somewhere in GuiIngame
private void attemptSwing() {
	if(ModInstances.getoldanim().isEnabled()) {
		if (this.mc.thePlayer.getItemInUseCount() > 0) {
			final boolean mouseDown = this.mc.gameSettings.keyBindAttack.isKeyDown() && this.mc.gameSettings.keyBindUseItem.isKeyDown();
			if (mouseDown && this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				this.swingItem(this.mc.thePlayer);
			}else if(mouseDown) {
				this.swingItem(this.mc.thePlayer);
			}
		}
	}
}

private void swingItem(EntityPlayerSP entityplayersp) {
	final int swingAnimationEnd = entityplayersp.isPotionActive(Potion.digSpeed) ? (6 - (1 + entityplayersp.getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1) : (entityplayersp.isPotionActive(Potion.digSlowdown) ? (6 + (1 + entityplayersp.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2) : 6);
	if (!entityplayersp.isSwingInProgress || entityplayersp.swingProgressInt >= swingAnimationEnd / 2 || entityplayersp.swingProgressInt < 0) {
		entityplayersp.swingProgressInt = -1;
		entityplayersp.isSwingInProgress = true;
	}
}
//and then call the attemptSwing method at updateTick()
public void updateTick()
{
	attemptSwing();
}
//Updated for simpler use
