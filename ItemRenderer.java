//Replace renderItemInFirstPerson() to this
public void renderItemInFirstPerson(float partialTicks)
    {
        float f = 1.0F - (this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * partialTicks);
        EntityPlayerSP entityplayersp = this.mc.thePlayer;
        float f1 = entityplayersp.getSwingProgress(partialTicks);
        float f2 = entityplayersp.prevRotationPitch + (entityplayersp.rotationPitch - entityplayersp.prevRotationPitch) * partialTicks;
        float f3 = entityplayersp.prevRotationYaw + (entityplayersp.rotationYaw - entityplayersp.prevRotationYaw) * partialTicks;
        this.func_178101_a(f2, f3);
        this.func_178109_a(entityplayersp);
        this.func_178110_a(entityplayersp, partialTicks);
        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();
        if (this.itemToRender != null)
        {
            if (this.itemToRender.getItem() instanceof ItemMap)
            {
                this.renderItemMap(entityplayersp, f2, f, f1);
            }
            else if (entityplayersp.getItemInUseCount() > 0)
            {
                EnumAction enumaction = this.itemToRender.getItemUseAction();

                switch (ItemRenderer.ItemRenderer$1.field_178094_a[enumaction.ordinal()])
                {
                    case 1:
                        this.transformFirstPersonItem(f, 0.0F);
                        break;

                    case 2:
                    case 3:
                        this.func_178104_a(entityplayersp, partialTicks);
                        this.transformFirstPersonItem(f, f1); //f,f1 - Enables the old animations while f, 0.0F disables it
                        break;

                    case 4:
                        this.transformFirstPersonItem(0.2F,f1);
                        this.func_178103_d();
                        GlStateManager.translate(-0.5F, 0.2F, 0.0F);
                        break;

                    case 5:
                        this.transformFirstPersonItem(f, f1);
                        this.func_178098_a(partialTicks, entityplayersp);
                }
            }
            else
            {
                this.func_178105_d(f1);
                this.transformFirstPersonItem(f, f1);
            }

            this.renderItem(entityplayersp, this.itemToRender, ItemCameraTransforms.TransformType.FIRST_PERSON);
        }
        else if (!entityplayersp.isInvisible())
        {
            this.func_178095_a(entityplayersp, f, f1);
        }

        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
    }
//Updated to look simpler
/* Explanation
 * f1 = the swing progress
 * aka the animation that plays when you attacked
 * so doing this.transformFirstPersonItem(f, f1); actually blocks AND plays the swing animation
 * while doing this.transformFirstPersonItem(f, 0.0F); actually STOPS the swing animation and immediately does the blocking
 */
