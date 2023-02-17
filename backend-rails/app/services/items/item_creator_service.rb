module Items
  class ItemCreatorService < BaseService
    attr_reader :params

    def call
      item = Item.new(params_with_user)

      authorize!(ItemPolicy, :create?, item)

      item.save!
      item
    end
  end
end
