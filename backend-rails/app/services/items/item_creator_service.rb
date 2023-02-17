module Items
  class ItemCreatorService < BaseService
    def call
      item = Item.new(params_with_user)

      authorize!(ItemPolicy, :create?, item)

      item.save!
      item
    end
  end
end
