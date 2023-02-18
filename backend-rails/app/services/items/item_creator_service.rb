module Items
  class ItemCreatorService < CreatorService
    def prepare_resource
      Item.new(params_with_user)
    end
  end
end
